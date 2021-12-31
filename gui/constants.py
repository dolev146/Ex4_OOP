from Classes.GraphAlgo import GraphAlgo
from gui.draw_arrow import draw_arrow

ga = GraphAlgo()

user_text_shortest = 'shortest'
user_text_tsp = 'tsp'
user_text_add_node = 'add node'
user_text_remove_node = 'remove node'
user_text_remove_edge = 'remove edge'
user_text_add_edge = 'add edge'
user_text_node_loc = 'hide location'
user_text_edge_weight = 'show weight'
user_text_save_json = 'save(filename)'

shortest_list = None
tsp_list = None

width = 1200
height = 700
incrementY = 100
incrementX = 100
minX = 100000000
maxX = (-1) * 100000000
minY = 100000000
maxY = (-1) * 100000000
scaleX = 0
scaleY = 0
factorX = 0
factorY = 0
center_node = None

node_list = []
src_edge_list = []
dest_edge_list = []
arrow_head_list = []


def getminmax():
    global minX
    global maxX
    global minY
    global maxY
    global scaleX
    global scaleY
    global factorX
    global factorY
    minX = 100000000
    maxX = (-1) * 100000000
    minY = 100000000
    maxY = (-1) * 100000000
    scaleX = 0
    scaleY = 0
    factorX = 0
    factorY = 0

    for node in ga.graph.get_all_v().values():
        if node.x is not None and node.y is not None:
            minX = min(minX, node.x)
            maxX = max(maxX, node.x)
            minY = min(minY, node.y)
            maxY = max(maxY, node.y)

    scaleX = abs(maxX - minX)
    scaleY = abs(maxY - minY)

    if scaleX == 0 or scaleY == 0:
        scaleX = 2
        scaleY = 1

    factorX = width / scaleX * 0.8
    factorY = height / scaleY * 0.8
    return [maxX, maxY, minX, minY]


def calculate_values():
    g = ga.get_graph()
    node_list.clear()
    src_edge_list.clear()
    dest_edge_list.clear()
    arrow_head_list.clear()
    for node in g.get_all_v().values():
        n_id = node.id
        if node.x is not None and node.y is not None and node.z is not None:
            x = (node.x - minX) * factorX + incrementX
            y = (node.y - minY) * factorY + incrementY
            node_list.append([x, y, n_id, node.x, node.y])

    for edge in ga.graph.Edges.values():
        if g.get_all_v().get(edge.src).x is not None and g.get_all_v().get(
                edge.src).y is not None and g.get_all_v().get(edge.dest).x is not None and g.get_all_v().get(
            edge.dest).y:
            x1 = incrementX + (
                    g.get_all_v().get(edge.src).x - minX) * factorX
            y1 = incrementY + (
                    g.get_all_v().get(edge.src).y - minY) * factorY
            x2 = incrementX + (
                    g.get_all_v().get(edge.dest).x - minX) * factorX
            y2 = incrementY + (
                    g.get_all_v().get(edge.dest).y - minY) * factorY
            if x1 > x2:
                y1 = y1 - 5
                y2 = y2 - 5
            else:
                y1 = y1 + 5
                y2 = y2 + 5
            src_edge_list.append([x1, y1, edge.w])
            dest_edge_list.append([x2, y2, edge.w])
            arrow_head_list.append(draw_arrow(x1, y1, x2, y2))
