from stem.control import Controller

# 9785BD5AC04E6112071EA9172591275465FD758D
# 2D91AA681F5702099581DDE4A3E410218D880ECB


n = int(input("Enter number of relays: "))
fingerprint = input("Enter RSA Fingerprint: ")

with Controller.from_port(port=9051) as controller:
    controller.authenticate()
    l = len(controller.get_circuits())
    print('Circuit list initially:')
    for i in controller.get_circuits():
        print('\t', end="")
        print(i)
    print()
    for i in range(n - l):
        controller.new_circuit(path=[fingerprint])

    print('Final circuit list:')
    for i in controller.get_circuits():
        print('\t', end="")
        print(i)
