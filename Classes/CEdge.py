class CEdge:
    def __init__(self, src=None, dest=None, w=None, info="white") -> None:
        self.src = src
        self.dest = dest
        self.w = w
        self.info = info

    def __str__(self) -> str:
        return f""" CEdge(src= {self.src} , dest= {self.dest} , w= {self.w}, info= {self.info}) \n"""

    def __repr__(self) -> str:
        return f""" CEdge(src= {self.src} , dest= {self.dest} , w= {self.w}, info= {self.info}) \n"""
