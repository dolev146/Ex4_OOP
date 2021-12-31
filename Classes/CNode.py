class CNode:

    def __init__(self, node_id_paramter: int = None, pos: tuple = None, info: str = "white", previous=None, length=None,
         rank: int = 0):
        if pos is None:
            self.x = None
            self.y = None
            self.z = None
        else:
            self.x = pos[0]
            self.y = pos[1]
            self.z = pos[2]
        self.id = node_id_paramter
        self.info = info
        self.previous = previous
        self.length = length
        self.rank = rank

    def __lt__(self, other):
        return self.length<other.length
      
    def __str__(self) -> str:
        return f"""( CNode id= {self.id} , info= {self.info} , previous= {self.previous} , length= {self.length},
         rank=' {self.rank} , pos= {self.x} , {self.y}, {self.z} ) \n"""

    def __repr__(self) -> str:
        return f""" ( CNode id= {self.id} , info= {self.info} , previous= {self.previous} , length= {self.length},
         rank=' {self.rank} , pos= {self.x} , {self.y}, {self.z} ) \n"""

