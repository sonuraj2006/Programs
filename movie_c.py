# cook your dish here
t_cases = int(input())
for _ in range(t_cases):
    x = list(map(int, input().split()))
    combos = min(x[0], x[1])
    remaining_tickets = x[0] - combos
    remaining_popcorn = x[1] - combos
    ans = (combos * x[4]) + (remaining_tickets * x[2]) + (remaining_popcorn * x[3])
    
    print(ans)
