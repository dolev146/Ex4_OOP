import random

from Classes.CEdge import CEdge
from interfaces.GraphInterface import GraphInterface
from Classes.CNode import CNode


class DiGraph(GraphInterface):

    def __init__(self, Nodes: dict = None, Edges: dict = None, graph=None):
        self.EdgesOut = {}
        self.EdgesIn = {}
        self.MC = 0
        if (Nodes == None):
            self.Nodes = {}
        else:
            self.Nodes = Nodes
        if (Edges == None):
            self.Edges = {}
        else:
            self.Edges = Edges
            for e in self.Edges:
                self.EdgesIn[e["src"]] = self.EdgesIn.get(e["src"], {}) + [{e["dest"]: e["w"]}]
                self.EdgesOut[e["dest"]] = self.EdgesOut.get(e["dest"], {}) + [{e["src"]: e["w"]}]

    def v_size(self) -> int:
        """
        @return: The number of vertices in this graph
        """
        return len(self.Nodes)

    def e_size(self) -> int:
        """
        Returns the number of edges in this graph
        @return: The number of edges in this graph
        """
        return len(self.Edges)

    def get_mc(self) -> int:
        """
        Returns the current version of this graph,
        on every change in the graph state - the MC should be increased
        @return: The current version of this graph.
        """
        return self.MC

    def add_edge(self, id1: int, id2: int, weight: float) -> bool:
        """
        Adds an edge to the graph.
        @param id1: The start node of the edge
        @param id2: The end node of the edge
        @param weight: The weight of the edge
        @return: True if the edge was added successfully, False o.w.
        Note: If the edge already exists or one of the nodes dose not exists the functions will do nothing
        https://stackoverflow.com/questions/1781571/how-to-concatenate-two-dictionaries-to-create-a-new-one-in-python
        """
        # dont know why this now working
        # if id1 or id2 not in self.Nodes:
        #     return False
        if id2 == id1:
            return False
        if id1 not in self.Nodes:
            return False
        if id2 not in self.Nodes:
            return False
        if f"{id1}_{id2}" not in self.Edges:
            self.Edges[f"{id1}_{id2}"] = CEdge(src=id1, dest=id2, w=weight)
            self.EdgesIn[id2].update({id1: (id1, weight)})
            self.EdgesOut[id1].update({id2: (id2, weight)})
            self.MC = self.MC + 1
            return True
        return False

    def remove_node(self, node_id: int) -> bool:
        """
        Removes a node from the graph.
        @param node_id: The node ID
        @return: True if the node was removed successfully, False o.w.
        Note: if the node id does not exists the function will do nothing
        https://stackoverflow.com/questions/5384914/how-to-delete-items-from-a-dictionary-while-iterating-over-it
        """
        if node_id in self.Nodes:
            del self.Nodes[node_id]
            del self.EdgesOut[node_id]
            del self.EdgesIn[node_id]
            for key in list(self.Edges.keys()):
                src_key = int(key.split("_")[0])
                dest_key = int(key.split("_")[1])
                if src_key == node_id:
                    del self.Edges[f"{src_key}_{dest_key}"]
                    # del self.EdgesIn[dest_key][src_key]
                    # del self.EdgesOut[dest_key][src_key]
                    self.EdgesOut[dest_key].pop(src_key, None)
                    self.EdgesIn[dest_key].pop(src_key, None)

                if dest_key == node_id:
                    del self.Edges[f"{src_key}_{dest_key}"]
            self.MC = self.MC + 1
            return True
        return False

    def remove_edge(self, node_id1: int, node_id2: int) -> bool:
        """
        Removes an edge from the graph.
        @param node_id1: The start node of the edge
        @param node_id2: The end node of the edge
        @return: True if the edge was removed successfully, False o.w.
        Note: If such an edge does not exists the function will do nothing
        https://stackoverflow.com/questions/11277432/how-can-i-remove-a-key-from-a-python-dictionary
        """
        if node_id2 == node_id1:
            return False
        if node_id1 not in self.Nodes:
            return False
        if node_id2 not in self.Nodes:
            return False
        if f"{node_id1}_{node_id2}" in self.Edges:
            del self.Edges[f"{node_id1}_{node_id2}"]
            del self.EdgesOut[node_id1][node_id2]
            del self.EdgesIn[node_id2][node_id1]
            self.MC = self.MC + 1
            return True
        return False

    def add_node(self, node_id: int, pos: tuple = None) -> bool:
        """
        Adds a node to the graph.
        @param node_id: The node ID
        @param pos: The position of the node
        @return: True if the node was added successfully, False o.w.
        Note: if the node id already exists the node will not be added
        """

        if pos == None:
            x = None
            y = None
            z = None
            pos = (x, y, z)

        if node_id not in self.Nodes:
            self.Nodes[node_id] = CNode(node_id_paramter=node_id, pos=pos)
            self.EdgesOut[node_id] = {}
            self.EdgesIn[node_id] = {}
            self.MC = self.MC + 1
            return True
        return False

    def get_all_v(self) -> dict:
        """return a dictionary of all the nodes in the Graph, each node is represented using a pair
         (node_id, node_data)
        """
        return self.Nodes

    def all_in_edges_of_node(self, id1: int) -> dict:
        """return a dictionary of all the nodes connected to (into) node_id ,
        each node is represented using a pair (other_node_id, weight)
         """
        return self.EdgesIn[id1]

    def all_out_edges_of_node(self, id1: int) -> dict:
        """return a dictionary of all the nodes connected from node_id , each node is represented using a pair
        (other_node_id, weight)
        """
        return self.EdgesOut.get(id1)

    def __str__(self) -> str:
        return f"""DiGraph(Edges= {self.Edges},EdgesIn={self.EdgesIn} ,EdgesOut={self.EdgesOut},MC={self.MC}) """

    def __repr__(self) -> str:
        return f"""DiGraph(Edges= {self.Edges},EdgesIn={self.EdgesIn} ,EdgesOut={self.EdgesOut},MC={self.MC}) """
