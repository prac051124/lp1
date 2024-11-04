from tkinter import *
root=Tk()
root.geometry("650x450")
root.title("Student Registration")

f1=Frame(padx=10,pady=10)
f1.grid(row=10,column=10)

m=Label(root,text="Hello")
n=Label(root,text="")

e=Entry(f1,width=27)
e.pack()
e.insert(0,"Enter name: ")

def click():
    mm=Label(f1,text=e.get())
    mm.pack()

b=Button(f1,text="CLICK ME!!!",padx=10,pady=10,command=click,fg="blue")
b.pack()

m.grid(row=0,column=0)
n.grid(row=0,column=1)


root.mainloop()
