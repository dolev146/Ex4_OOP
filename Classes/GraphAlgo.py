import heapq
import random
import sys
from math import inf

from typing import List

from Classes import SaveLoad
from interfaces.GraphAlgoInterface import GraphAlgoInterface
from interfaces.GraphInterface import GraphInterface


class GraphAlgo(GraphAlgoInterface):

    def __init__(self, graph=None):
        self.graph = graph

    def init(self, graph):
        self.graph = graph

    def load_from_json(self, file_name: str) -> bool:
        self.graph = SaveLoad.load(file_name)
        a = 5

    def save_to_json(self, file_name: str) -> bool:
        return SaveLoad.save(file_name, self.graph)

    def shortest_path(self, id1: int, id2: int) -> (float, list):
        graph = self.graph
        listQ = []

        for node in graph.Nodes.values():
            node.length = sys.maxsize
            node.previous = None
            listQ.append(node)
        graph.Nodes.get(id1).length = 0
        heapq.heapify(listQ)

        while not len(listQ) == 0:
            current = heapq.heappop(listQ)
            dictOfCurrent = graph.all_out_edges_of_node(current.id)
            for TupleNode in dictOfCurrent.values():
                dist = current.length + TupleNode[1]
                if dist < graph.Nodes.get(TupleNode[0]).length:
                    graph.Nodes.get(TupleNode[0]).length = dist
                    graph.Nodes.get(TupleNode[0]).previous = current.id
                    heapq.heapify(listQ)

        idtemp = id2
        listNodeId = []
        while not idtemp == None:
            listNodeId.insert(0, idtemp)
            idtemp = graph.Nodes.get(idtemp).previous

        if graph.Nodes.get(id2).length == sys.maxsize:
            return (inf, [])

        return (graph.Nodes.get(id2).length, listNodeId)

    def plot_graph(self) -> None:
        '''
        first we need to check min and max for the excidting nodes , else it will be based on actual resolution .
        first check if everyone has postion , then act .

        '''
        counter = 0
        minX = 100000000
        maxX = (-1) * 100000000
        minY = 100000000
        maxY = (-1) * 100000000

        for node in self.get_graph().get_all_v().values():
            if node.x == None and node.y == None and node.z == None:
                counter += 1
            else:
                minX = min(minX, node.x)
                maxX = max(maxX, node.x)
                minY = min(minY, node.y)
                maxY = max(maxY, node.y)

        if counter != 0:
            if counter == self.get_graph().v_size():
                minX = 20
                maxX = 35
                minY = 20
                maxY = 30

            if counter == (self.get_graph().v_size() - 1):
                minX += -5
                maxX += 5
                minY += -5
                maxY += 5

        for node in self.get_graph().get_all_v().values():
            if node.x == None and node.y == None and node.z == None:
                node.x = random.uniform(minX, maxX)
                node.y = random.uniform(minY, maxY)
                node.z = 0.0

    def get_graph(self) -> GraphInterface:
        return self.graph

    def TSP(self, node_lst: List[int]) -> (List[int], float):
        listSave = []
        distance = 0

        while len(node_lst) > 1:
            saveTuple = self.shortest_path(node_lst[0], node_lst[1])
            listSave += saveTuple[1]
            distance += saveTuple[0]
            listSave.pop()
            for i in node_lst:
                if i in listSave:
                    node_lst.remove(i)
        listSave.append(node_lst.pop())
        return (listSave, distance)

    def centerPoint(self) -> (int, float):
        graph = self.graph
        saveLength = sys.maxsize
        idsave = -1
        templength = -1

        for node in graph.Nodes.values():  # active shortestPath on all the nodes
            self.shortest_path(node.id, 0)

            for node2 in graph.Nodes.values():  # take the longest length
                if templength < node2.length:
                    templength = node2.length

            if templength < saveLength:  # take the shortest from the longest
                saveLength = templength
                idsave = node.id
            templength = -1

        return (idsave, saveLength)


if __name__ == '__main__':
    ga = GraphAlgo()
    ga.load_from_json("../../data_ex3/A5.json")
    # ga.graph.all_in_edges_of_node(1)
    # b=ga.shortest_path(2,20)
    # c=ga.TSP([8,5,2,4,3])
    d = ga.centerPoint()
    print(d)
