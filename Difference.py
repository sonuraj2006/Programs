t = int(input()) 
for _ in range(t):
    n = int(input()) # Size of the array
    a = list(map(int, input().split())) # The array A
    counts = {}
    ans = 0
    for i in range(n):
        val = a[i] - (i + 1)
        if val in counts:
            ans += counts[val]
            counts[val] += 1
        else:
            counts[val] = 1  
    print(ans)
