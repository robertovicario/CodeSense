import tkinter as tk
import timeit

def compare_code():
    code1 = code1_text.get("1.0", tk.END)
    code2 = code2_text.get("1.0", tk.END)
    time1 = timeit.timeit(code1, number=10000)
    time2 = timeit.timeit(code2, number=10000)
    result = f"The first code is more efficient than the second one.\nExecution time:\nCode 1: {time1}\nCode 2: {time2}"
    
    if time2 < time1:
        result = f"The second code is more efficient than the first one.\nExecution time:\nCode 1: {time1}\nCode 2: {time2}"
    elif time1 == time2:
        result = f"Both codes have the same execution time.\nExecution time:\nCode 1: {time1}\nCode 2: {time2}"
    
    result_label.config(text=result)

window = tk.Tk()
window.title("CodeSense")

code1_label = tk.Label(window, text="Code 1:")
code1_label.pack()

code1_text = tk.Text(window, height=10, width=50)
code1_text.pack()

code2_label = tk.Label(window, text="Code 2:")
code2_label.pack()

code2_text = tk.Text(window, height=10, width=50)
code2_text.pack()

compare_button = tk.Button(window, text="Compare", command=compare_code)
compare_button.pack()

result_label = tk.Label(window, text="")
result_label.pack()

window.mainloop()
