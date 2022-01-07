
# Ex4 OOP Ariel Univercity ✨

![image](https://user-images.githubusercontent.com/62290677/148532227-1c262d00-fbc2-41b2-b575-61bda8842b00.png)

after implementing graphs algorithm's: 

- TSP()
- ShortestPath()
- Center()

now we made a game out of it 🕹


## Run Locally

Clone the project

```bash
  git clone https://github.com/dolev146/Ex4_OOP.git
```

Go to the project directory

```bash
  cd Ex4_OOP
```

Install dependencies

```bash
  pip install -r requirements.txt
```

Start the server make sure you have java installed case 0 - 15 ,
the number in the end of the line is the case number

```bash
     java -jar Ex4_Server_v0.0.jar 0
```

***and on a diffrent terminal***


```bash
  cd Ex4_OOP
```

```bash
windows
    python Ex4.py

mac
    python3 Ex4.py
```

## Gui Demo
press the image to go to the video

[![IMAGE ALT TEXT HERE](https://user-images.githubusercontent.com/62290677/148537804-9348449d-f5e9-40f8-b6c9-8c5aa363b034.png)](https://www.youtube.com/watch?v=b8LmH218lmQ)


## Thought planning and execution 

First we had to choose a language, we chose Python because it was faster to develop in Python than in Jaba a lesson we learned from previous assignments, however we had to do more sessions to understand what to do and what variables are going through, i.e. to strengthen communication. At the beginning of the task we had to think about what format to work in, and how in general the information flows in the software, how to access it, what names we call variables and cause uncertainty to become absolute certainty of a form of work. After we created a working skeleton of the software, we divided the task between us each what he does best. Daniel did the part of the algorithm, Jacob worked on the position of the Pokemon and a function that prevents the number of calls to move to go through the 10 times per second and on the display, Dolev worked on the combination of information between the two things so that they do not collide. And finally we managed to get to something that works well in a relatively short time. also python got us better resolts in the time comparison

## design patterns used
![image](https://user-images.githubusercontent.com/62290677/148535763-eb676c90-1722-4d5b-8a54-74770877f29e.png)


- MVC - we seperated the calculation of the data from the importing and exporting the data from the server functions so that a function that run an algoritm only take care of calculation , and a function that deals with server only take care interacting with the server. inspired from React by using a single source of truth.
- singleton - we used a singelton + state design patterns so that all the code is referencing one specific objects that are located in the file settings.py and in every use of them you need to import them to use them.


## Unit Tests

inside file Ex4Test.py

![image](https://user-images.githubusercontent.com/62290677/148533376-0afed417-37c6-442b-b814-9156dc2e7127.png)

![image](https://user-images.githubusercontent.com/62290677/148533490-f5446fa0-f24d-4890-9c41-dce8115abbba.png)
### Algorithms functions 

* make_decisions() - This function detects when the agent has reached the position he needs,
     calculates the next step and adds it to settings.move_list
     settings.move_list.append({"agent_id": agent.id , "next_node_id": next_node)
     *** note it will only add a move list when it is not exist
     we chose the aproach of using greedy algorithm, that sends the agents to the closest pokemon by using the tsp function we made in ex3 to compare the pokemons and choose the agents paths
 
* pokemon.set_edge() - tell me where is the pokimon located on which edge so that we can tell what is the node we need to go in order the catch the pokemon.








## Authors

- [@ Dolev Dublon](https://github.com/dolev146)
- [@ Daniel Zaken](https://github.com/daniel555666)
- [@ Yakov Khodorkovski](https://github.com/yakov103)



# Ex3 OOP Ariel Univercity

![image](https://user-images.githubusercontent.com/62290677/147416924-927bcdcb-8cf6-4b95-82a0-ef475f122183.png)

the project is about implementing graphs algorithm's: 

- TSP()
- ShortestPath()
- Center()

The software also has a gui to give easier use.


## Run Locally

Clone the project

```bash
  git clone https://github.com/dolev146/Ex3.git
```

Go to the project directory

```bash
  cd Ex3
```

Install dependencies

```bash
  pip install -r requirements.txt
```

Start the Gui

```bash
windows
    python Ex4.py ./data_ex3/A0.json

mac
    python3 Ex4.py ./data_ex3/A0.json
```

please watch the demo to know the specific input sytax.
## Gui Demo

![image](https://user-images.githubusercontent.com/62290677/147415610-540a000c-b81d-4b5d-a52a-782112ef570f.png)
![image](https://user-images.githubusercontent.com/62290677/147415622-e51f93a0-b78f-45e1-aec6-41340b55e5a2.png)
![image](https://user-images.githubusercontent.com/62290677/147415636-1a498b7e-6426-49c1-ac71-a182c3b1bd14.png)
**to use the tsp input box for example you will need to**
- insert the id numbers of the node seperated by comma (' , ')
- press the Return button on the keyboard AKA Enter button

**watch the YouTube video explaining everything**
[![IMAGE ALT TEXT HERE](https://user-images.githubusercontent.com/62290677/147417843-89756c59-f705-4bda-b28d-c2324b4f7161.png)](https://www.youtube.com/watch?v=rk7QZly6sPE)

### Test our code

![image](https://user-images.githubusercontent.com/62290677/147556840-a31223db-600a-421b-b621-d58a03e749df.png)

just follow the instructions in the Ex3.py , how to disable the gui and print the results you wish to check

## Lessons Learned

we learned to work as a team with git and github.
we learend that in order to work better we first need to to plan
before we start writing the code. 
## Unit Tests

iside the [Tests directory](https://github.com/dolev146/Ex3/tree/main/Tests) you can see all the tests

![image](https://user-images.githubusercontent.com/62290677/147417425-0c38ae67-cb5f-4efe-bcf3-974d0867d4ac.png)

![image](https://user-images.githubusercontent.com/62290677/147417448-e87d4741-0427-49af-aa41-5511010f7ad4.png)

## RunTime compare

![image](https://user-images.githubusercontent.com/62290677/147417470-b8eced3e-f98e-4c74-abb0-64e6553befa5.png)
and took the result from the terminal for example:

![image](https://user-images.githubusercontent.com/62290677/147417688-f04ecc5c-e5cc-4d6c-92ea-6fae3eed0f6e.png)

| comapre vs python     | table |
| ----------- | ----------- |
| ![image](https://user-images.githubusercontent.com/62290677/147417488-8c9dba7e-3e9e-4d24-9dde-a630725ea5fc.png)      | ![image](https://user-images.githubusercontent.com/62290677/147417549-6f459c83-7d12-40e8-b94c-4253f349d487.png) |
| ![image](https://user-images.githubusercontent.com/62290677/147417580-6a682fe1-9343-48ee-b314-77a83e10a4a0.png)   | ![image](https://user-images.githubusercontent.com/62290677/147417605-0bd88385-5e85-4a2c-94b5-0efc25c9c5e3.png)        |
| ![image](https://user-images.githubusercontent.com/62290677/147417617-f793c2b8-df47-40dd-9f35-0b2c46aaa296.png)   | ![image](https://user-images.githubusercontent.com/62290677/147417622-208cd026-4435-4e5a-975f-3190f7c43081.png)        |
| ![image](https://user-images.githubusercontent.com/62290677/147419000-45e3a2d2-55a0-4a34-b256-a53e265212e9.png) | ![image](https://user-images.githubusercontent.com/62290677/147419008-aec9558b-04d9-4922-8d00-534dd1d7376b.png) |
| ![image](https://user-images.githubusercontent.com/62290677/147419017-3bfef0e6-9b25-44c3-a380-9e19dce7bd3d.png) | ![image](https://user-images.githubusercontent.com/62290677/147419028-3d810803-a0c4-4751-b96d-322a57213b63.png) |

![image](https://user-images.githubusercontent.com/62290677/147501138-c1e7f5d3-04bf-435c-96ba-c9646dacb857.png)
windows 10 pro


### data structure 

the Graph captures the data in 4 Dictionaries . 
* dictionary for the nodes  - implementing editing nodes by their id in O(1)
* dictionary for the edges  - implementing editing edges by taking the sourse and destantion of the edge, and convert it to 1 string (```src_dest```) key that gives us the edge on O(1) and 2 other dictionaries for in and out edges.


### implemented classes 

* **CNode** - Node of graph. 
* **CEdge** - Edges of graph. 
* **DiGraph**  - implementing GraphInterface 
* **GraphAlgo** - implementing GraphAlgoInterface



### Algorithms functions 
 
* center - This functions return the Node Id that in the center of the graph (relative to the rest of the vertices) and his distance.In this function we use the dijkstra algorithm to caculte the length from node to the other nodes and this help us to find the center node(vertex).
 
* tsp - This function get list of nodes(vertex) Id's and return list of nodes Id's that present the path that pass in the nodes that we get and the overall distance,also the function return "good" path and not path that pass to mach vertex and visit vertex that not necessaries .We use shortest_path function to find the best path from node to node.

* shortestPath - This function get src and dest Id and return Node list of all the Nodes Id's that in the shortest path in the same order as they show up in the path.

* Plot graph - This function search in the init graph empty nodes  and fill them with random cordinates depending on the scale :
If the graph is made from empty nodes , the scale will be automatic else, the function will find the min and max nodes in the graph to get the scaling , and combined them .
If there is only 1 node in the graph with cordinates, the other new random nodes will be around him .



## classes functions

### CEdge class functions
* src
* dest
* w
* info

### CNode class functions
* id
* info 
* previous 
* length 
* rank 
* x
* y
* z


### DiGraph (implements Graph interface) class functions
* v_size
* e_size
* get_all_v
* all_in_edges_of_node
* all_out_edges_of_node()
* get_mc()
* add_edge(int node_id)
* add_node(int node_id)
* remove_node
* remove_edge

### GraphAlgo (implements GraphAlgoInterface) class functions 
* init
* get_graph
* shortest_path
* centerPoint
* TSP
* Plot graph
* save_to_json
* load_from_json

# UML

![new image](https://user-images.githubusercontent.com/62290677/147418549-2a09e887-b739-4b54-b736-de94b72482e8.png)


 

## Authors

- [@ Dolev Dublon](https://github.com/dolev146)
- [@ Daniel Zaken](https://github.com/daniel555666)
- [@ Yakov Khodorkovski](https://github.com/yakov103)

