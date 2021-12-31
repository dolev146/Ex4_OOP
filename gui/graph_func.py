import gui.constants
from gui.draw_arrow import draw_arrow


def reset_add_node_string():
    gui.constants.user_text_add_node = 'add node'


def reset_remove_node_string():
    gui.constants.user_text_remove_node = 'remove node'


def reset_remove_edge_string():
    gui.constants.user_text_remove_edge = 'remove edge'


def reset_add_edge_string():
    gui.constants.user_text_add_edge = 'add edge'

def reset_node_loc_string():
    gui.constants.user_text_node_loc = 'hide location'

def add_node_g(user_text_add_node):
    split_str = user_text_add_node.split(",")
    if len(split_str) == 3:
        x = float(split_str[1])
        y = float(split_str[2])
        pos = (x, y, 0.0)
        node_id = int(split_str[0])
        value = gui.constants.ga.get_graph().add_node(node_id, pos)
        gui.constants.getminmax()
        gui.constants.calculate_values()
        return value
    elif len(split_str) == 1:
        value = gui.constants.ga.get_graph().add_node(int(split_str[0]))
        gui.constants.getminmax()
        gui.constants.calculate_values()
        return value


def remove_node_g(user_text_remove_node):
    input_str = user_text_remove_node
    if input_str.isnumeric():
        node_id = int(input_str)
        value = gui.constants.ga.get_graph().remove_node(node_id)
        gui.constants.getminmax()
        gui.constants.calculate_values()
        return value
    else:
        gui.constants.getminmax()
        gui.constants.calculate_values()
        return False


def remove_edge_g(user_text_remove_edge):
    input_str = user_text_remove_edge.split(",")
    if len(input_str) == 2:
        src_id = int(input_str[0])
        dest_id = int(input_str[1])
        value = gui.constants.ga.get_graph().remove_edge(src_id, dest_id)
        gui.constants.getminmax()
        gui.constants.calculate_values()
        return value
    else:
        gui.constants.getminmax()
        gui.constants.calculate_values()
        return False


def add_edge_g(user_text_add_edge):
    input_str = user_text_add_edge.split(",")
    if len(input_str) == 3:
        src_id = int(input_str[0])
        dest_id = int(input_str[1])
        weight = float(input_str[2])
        value = gui.constants.ga.get_graph().add_edge(src_id, dest_id, weight)
        gui.constants.getminmax()
        gui.constants.calculate_values()
        return value
    else:
        gui.constants.getminmax()
        gui.constants.calculate_values()
        return False


def node_loc_g():
    g = gui.constants.ga.get_graph()
    gui.constants.node_list.clear()
    gui.constants.src_edge_list.clear()
    gui.constants.dest_edge_list.clear()
    gui.constants.arrow_head_list.clear()
    for node in g.get_all_v().values():
        n_id = node.id
        x = (node.x - gui.constants.minX) * gui.constants.factorX + gui.constants.incrementX
        y = (node.y - gui.constants.minY) * gui.constants.factorY + gui.constants.incrementY
        gui.constants.node_list.append([x, y, n_id, node.x, node.y])

    for edge in gui.constants.ga.graph.Edges.values():
        x1 = gui.constants.incrementX + (
                g.get_all_v().get(edge.src).x - gui.constants.minX) * gui.constants.factorX
        y1 = gui.constants.incrementY + (
                g.get_all_v().get(edge.src).y - gui.constants.minY) * gui.constants.factorY
        x2 = gui.constants.incrementX + (
                g.get_all_v().get(edge.dest).x - gui.constants.minX) * gui.constants.factorX
        y2 = gui.constants.incrementY + (
                g.get_all_v().get(edge.dest).y - gui.constants.minY) * gui.constants.factorY
        if x1 > x2:
            y1 = y1 - 5
            y2 = y2 - 5
        else:
            y1 = y1 + 5
            y2 = y2 + 5
        gui.constants.src_edge_list.append([x1, y1])
        gui.constants.dest_edge_list.append([x2, y2])
        gui.constants.arrow_head_list.append(draw_arrow(x1, y1, x2, y2))
