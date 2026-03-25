T = int(input())

for _ in range(T):
    N = int(input())
    
    # If N is imperfect (divisible by 2 or 5, but NOT both)
    if (N % 2 == 0 or N % 5 == 0) and (N % 10 != 0):
        print(0)
    
    # If N is divisible by 10 (divisible by both 2 and 5)
    elif N % 10 == 0:
        print(2)
        
    # Otherwise, N is not divisible by 2 or 5
    else:
        print(1)
