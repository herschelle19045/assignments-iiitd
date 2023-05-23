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

static struct User {
    char addr[16];
    int nul, udp, syn;
    int size;
} user;

static bool flag = true;


static unsigned int intrusion_hook(void *priv, 
    struct sk_buff *skb, const struct nf_hook_state *state) {
    
    struct iphdr *iph;
    struct udphdr *udph;
    struct tcphdr *tcph;
    char source[16];

    if (!skb)
        return NF_ACCEPT;

    iph = ip_hdr(skb);
    udph = udp_hdr(skb);
    tcph = tcp_hdr(skb);

    
    snprintf(source, 16, "%pI4", &iph->saddr);

    // printk(KERN_INFO "%s\n", source);

    if(strcmp(source, "192.168.138.131") != 0) {
        return NF_ACCEPT;
    }


    strcpy(user.addr, source);

    if(flag) {
        flag = false;
        user.size = user.nul = user.udp = user.syn = 0;
    }


    if (iph->protocol == IPPROTO_UDP) {
        // if (ntohs(udph->dest) == 53) {
            
        // }
        // int port = ntohs(udph->dest);
        user.udp++;
        // printk(KERN_INFO "UDP Packet to port: %d\n", port);
        return NF_DROP;
    }

    else if (iph->protocol == IPPROTO_TCP) {
        int sum = tcph->syn + tcph->ack + tcph->urg 
            + tcph->rst + tcph->fin + tcph->psh;
        
        if(sum == 1) {
            if(tcph->syn == 1) {
                user.syn++;
                return NF_DROP;   
            }
        }
        else if(sum == 0) {
            user.nul++;
            return NF_DROP;   
        }
        
    }
    user.size++;
    // printk(KERN_INFO "Size = %d\n", user.size);

    // else if (iph->protocol == IPPROTO_ICMP) {
    //     printk(KERN_INFO "ICMP Packet recieved\n");
    // }

    
    return NF_ACCEPT;
}

static int __init initialize(void) {
    nfho = (struct nf_hook_ops*) kcalloc(1, sizeof(struct nf_hook_ops), GFP_KERNEL);
    
    nfho->hook  = (nf_hookfn*) intrusion_hook; 
    nfho->hooknum   = NF_INET_PRE_ROUTING;          
    nfho->pf    = PF_INET;                          
    nfho->priority  = NF_IP_PRI_FIRST;              
    
    nf_register_net_hook(&init_net, nfho);
    printk(KERN_INFO "Intrusion detection module loaded\n");
    return 0;
}

static void __exit remove(void) {
    nf_unregister_net_hook(&init_net, nfho);
    kfree(nfho);

    
    if(user.syn > 5) {
        printk(KERN_INFO "%d TCP Half Scans from %s", user.syn, user.addr);
    }
    if(user.nul > 5) {
        printk(KERN_INFO "%d Null Scans from %s", user.nul, user.addr);
    }
    if(user.udp > 5) {
        printk(KERN_INFO "%d UDP Scans from %s", user.udp, user.addr);
    }

    printk(KERN_INFO "Intrusion detection module unloaded");
}

module_init(initialize);
module_exit(remove);