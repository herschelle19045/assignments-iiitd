from concurrent import futures

import grpc

import blocking_pb2
import blocking_pb2_grpc

REGISTRY_PORT = '8888'

replicas = dict()
primary_server = None


class RegistryServer(blocking_pb2_grpc.RegistryServerServicer):

    def RegisterServer(self, request, context):
        global primary_server
        print(f"JOIN REQUEST FROM {request.name}, {request.ipaddress}")
        if primary_server is None:
            primary_server = request.ipaddress
            primary = True
        else:
            primary = False
        replicas[request.name] = request.ipaddress
        status = "SUCCESS"
        return blocking_pb2.RegisterResponse(status=status, primary_ipaddress=primary_server, primary=primary)

    def GetServerList(self, request, context):
        server_list = []
        for key in replicas:
            server_list.append(key + "-" + replicas[key])
        status = "SUCCESS"
        return blocking_pb2.ServerListResponse(status=status, listOfServers=server_list)


def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    blocking_pb2_grpc.add_RegistryServerServicer_to_server(RegistryServer(), server)
    server.add_insecure_port('[::]:' + REGISTRY_PORT)
    server.start()
    print("Registry Server started, listening on " + REGISTRY_PORT)
    server.wait_for_termination()


if __name__ == '__main__':
    try:
        serve()
    except KeyboardInterrupt:
        print("Server shutting down")
