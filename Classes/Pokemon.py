import math

import settings


class Pokemon:
    def __init__(self, value: float = None, type: int = None, pos: str = None) -> None:
        self.value = value
        self.type = type
        self.pos = pos
        split_pos = self.pos.split(",")
        self.x = float(split_pos[0])
        self.y = float(split_pos[1])
        self.z = float(split_pos[2])
        self.win_node_src = None
        self.win_node_dest = None

    def __str__(self) -> str:
        return f""" pokemon(value= {self.value} , type= {self.type} , pos= {self.pos} \n"""

    def __repr__(self) -> str:
        return f""" pokemon(value= {self.value} , type= {self.type} , pos= {self.pos} \n"""

    def set_edge(self):
        """
         edgelist
         "src_dest"
         tell me where is the pokimon located
        """
        # print("pok pos : " + " x: " + str(self.x) + "y : " + str(self.y) + "type: " + str(self.type))
        for distance in settings.distance_edges:
            src = settings.graph.Nodes.get(distance["src"])
            dest = settings.graph.Nodes.get(distance["dest"])
            d1 = distance["value"]
            d2 = math.dist([self.x, self.y], [src.x, src.y])
            d3 = math.dist([self.x, self.y], [dest.x, dest.y])
            if d1 - 0.000001 < d2 + d3 < d1 + 0.000001:
                if self.type > 0 and dest.id > src.id:
                    self.win_node_src = src
                    self.win_node_dest = dest
                    # print(f"from {src.id} to {dest.id} is on  ")

                elif self.type < 0 and dest.id < src.id:
                    self.win_node_src = src
                    self.win_node_dest = dest
                    # print(f"from {src.id} to {dest.id} is on  ")
