class Pokemon:
    def __init__(self, value: float = None, type: int = None, pos: str = None) -> None:
        self.value = value
        self.type = type
        self.pos = pos
        split_pos = self.pos.split(",")
        self.x = float(split_pos[0])
        self.y = float(split_pos[1])
        self.z = float(split_pos[2])
        self.edge = None
        self.src = None
        self.dest = None

    def __str__(self) -> str:
        return f""" pokemon(value= {self.value} , type= {self.type} , pos= {self.pos} \n"""

    def __repr__(self) -> str:
        return f""" pokemon(value= {self.value} , type= {self.type} , pos= {self.pos} \n"""

    def set_edge(self, edge_list):
        """
         מקבלים ליסט שהוא ה ערך של הצלעות, edgelist
         "src_dest"
         tell me where is the pokimon located
        """
        pass


