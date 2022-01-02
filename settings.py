# default port
from Classes.DiGraph import DiGraph
from Classes.GraphAlgo import GraphAlgo

processes = []

PORT = 6666
# server host (default localhost 127.0.0.1)
HOST = '127.0.0.1'
client = None
agents_amount = 0
agents = []
pokemons = []
graph = DiGraph()
algo = GraphAlgo()
eps1 = 0.001
eps2 = 0.000001
epsilon = 0.000001

# game server info from json
pokemons_amount = 0
is_logged_in = False
moves = 0
grade = 0
game_level = 0
max_user_level = -1
info_id = 0
info_graph = ""
