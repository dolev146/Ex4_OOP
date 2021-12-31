import gui.constants
from gui import constants


def reset_tsp_string():
    gui.constants.user_text_tsp = 'tsp'

def reset_shortest_string():
    gui.constants.user_text_shortest = 'shortest'

def reset_center():
    constants.center_node = None


def reset_tsp():
    constants.tsp_list = None


def reset_shortest():
    constants.shortest_list = None

def reset_save_json_string():
    constants.user_text_save_json = 'save(filename)'


def center_ga():
    (center_n, a) = constants.ga.centerPoint()
    constants.center_node = center_n


def tsp_ga(user_text_tsp):
    split_str = user_text_tsp.split(",")
    node_id_list = []
    for s in split_str:
        node_id_list.append(int(s))
    (tsp_l, cost) = constants.ga.TSP(node_id_list)
    constants.tsp_list = tsp_l
    print(constants.tsp_list)
    return cost


def shortest_ga(user_text_shortest):
    split_str = user_text_shortest.split(",")
    node_id_list = []
    for s in split_str:
        node_id_list.append(int(s))
    (cost, shortest_l) = constants.ga.shortest_path(node_id_list[0], node_id_list[1])
    constants.shortest_list = shortest_l
    print(constants.shortest_list)
    return cost

def save_json_ga(user_text_save_json):
    cost = constants.ga.save_to_json(user_text_save_json)
    return cost

