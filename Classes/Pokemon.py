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
        self.win_node = None

    def __str__(self) -> str:
        return f""" pokemon(value= {self.value} , type= {self.type} , pos= {self.pos} \n"""

    def __repr__(self) -> str:
        return f""" pokemon(value= {self.value} , type= {self.type} , pos= {self.pos} \n"""

    def set_edge(self):
        """
         מקבלים ליסט שהוא ה ערך של הצלעות, edgelist
         "src_dest"
         tell me where is the pokimon located
        """
        print("pok pos : " + " x: " + str(self.x) + "y : " + str(self.y) + "type: " + str(self.type))
        for distance in settings.distance_edges:
            src = settings.graph.Nodes.get(distance["src"])
            dest = settings.graph.Nodes.get(distance["dest"])
            d1 = distance["value"]
            d2 = math.dist([self.x, self.y], [src.x, src.y])
            d3 = math.dist([self.x, self.y], [dest.x, dest.y])
            if distance["src"] == 9 and distance["dest"] == 8:
                if d1 - 0.00001 < d2 + d3 < d1 + 0.00001:
                    self.win_node = src

        a = 5

        # for yashar in settings.edges_plus:
        #     if yashar["src"] == 8 and yashar["dest"] == 9:
        #         print("point")
        #
        #     y = yashar["m"] * self.x + yashar["c"]
        #     if y - 0.001 < self.y < y + 0.001:
        #         self.src = yashar["src"]
        #
        # for yashar in settings.edges_minus:
        #     if yashar["src"] == 9 and yashar["dest"] == 8:
        #         print("point")
        #
        #     y = yashar["m"] * self.x + yashar["c"]
        #     if y - 0.001 < self.y < y + 0.001:
        #         self.src = yashar["src"]

        # if self.type > 0:
        #     for yashar in settings.edges_plus:
        #         y = yashar["m"] * self.x + yashar["c"]
        #         if y - 0.001 < self.y < y + 0.001:
        #             self.src = yashar["src"]
        # else:
        #     for yashar in settings.edges_minus:
        #         y = yashar["m"] * self.x + yashar["c"]
        #         if y - 0.001 < self.y < y + 0.001:
        #             self.src = yashar["src"]
