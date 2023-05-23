#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/netfilter.h>
#include <linux/netfilter_ipv4.h>
#include <linux/ip.h>
#include <linux/tcp.h>
#include <linux/udp.h>
#include <linux/icmp.h>
#include <linux/string.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Herschelle Gupta");
MODULE_DESCRIPTION("Intrusion detection module");
MODULE_VERSION("1.0");

static struct nf_hook_ops *nfho = NULL;



static unsigned int intrusion_hook(void *priv, 
    struct sk_buff *skb, const struct nf_hook_state *state) {
    
    struct iphdr *iph;
    struct tcphdr *tcph;

    char source[16];


    if (!skb)
        return NF_ACCEPT;

    iph = ip_hdr(skb);
    tcph = tcp_hdr(skb);

    
    snprintf(source, 16, "%pI4", &iph->saddr);

    // printk(KERN_INFO "%s", source);

    if(strcmp(source, "127.0.0.1") != 0) {
        return NF_ACCEPT;
    }

    if (iph->protocol == IPPROTO_TCP) {

        if (skb->data_len == 1024) {
            tcph->doff++;
        }
    }

    
    return NF_ACCEPT;
}

static int __init initialize(void) {
    nfho = (struct nf_hook_ops*) kcalloc(1, sizeof(struct nf_hook_ops), GFP_KERNEL);
    
    nfho->hook  = (nf_hookfn*) intrusion_hook; 
    nfho->hooknum   = NF_INET_PRE_ROUTING;          
    nfho->pf    = PF_INET;                          
    nfho->priority  = NF_IP_PRI_FIRST;              
    
    nf_register_net_hook(&init_net, nfho);
    return 0;
}

static void __exit remove(void) {
    nf_unregister_net_hook(&init_net, nfho);
    kfree(nfho);
}

module_init(initialize);
module_exit(remove);