# Freeplane_Unicode_Progress_Bar
 
A little **add-on** to add **Unicode Progress Bars** to the nodes in Freeplane.

### ATTENTION!! This add-on displays the bar correctly only when using some of the fonts types. I use, for example, **_MS UI Gothic_**



## Here are some sample images:

![image](https://user-images.githubusercontent.com/35700575/207910765-8f57386b-4bf3-4e23-b136-5ce6dd0ee4e3.png)

----

to get something like this

![image](https://user-images.githubusercontent.com/35700575/207911950-76d38b18-1c9f-4a0a-8a49-f323aa0df493.png)

you just have to use this formula:
```groovy
=
import edofro.freeplane.unicodeprogressbar.PBar as pb

pb.progressBar('35%')
```

----

it can be used in node's texts, details or attributes:

![image](https://user-images.githubusercontent.com/35700575/207911008-fc62500f-750d-4734-906c-2a6bd78ffb3a.png)

----

It includes help methods to make the bars relative to the node's siblings values:  
More information in the **examples mindmap**

![image](https://user-images.githubusercontent.com/35700575/207911312-b1287b13-9408-434c-8fb8-94299450990e.png)

----

you can use this `edofro.freeplane.unicodeprogressbar.PBar` library to include it in a script or formula that does what you need. 

For example, to do this:

![image](https://user-images.githubusercontent.com/35700575/208102999-2a7d1a0c-2ffb-4105-8a92-b6eae1fe2109.png)

y wrote the following formula in nodes details:

```groovy
=
import edofro.freeplane.unicodeprogressbar.PBar as pb

def done = node.children.count{ it.icons.contains('checked') }
def pending = node.children.count{ it.icons.contains('unchecked') }

pb.progressBar(done, done+pending)
```

or this formula:

```groovy
=
import edofro.freeplane.unicodeprogressbar.PBar as pb

def done    = node.children.count{ it.icons.contains('checked'  ) }
def pending = node.children.count{ it.icons.contains('unchecked') }

pb.progressBar(done,(done+pending).toDouble())
```

to get it in percentage:

![image](https://user-images.githubusercontent.com/35700575/208104263-c56455af-847a-4f08-89c2-c9a8b93bec13.png)

----

The add-on can be found here:  
https://github.com/EdoFro/Freeplane_Unicode_Progress_Bar/releases/tag/v0.0.1

there is also a **mindmap with example use cases**


The addon's homepage is here:  
https://github.com/EdoFro/Freeplane_Unicode_Progress_Bar


I hope you like it. Feedback is very welcome.

greetings,

edo

