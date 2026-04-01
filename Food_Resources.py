import sys
def solve():
    data = sys.stdin.read().split()
    if not data: return
    n = int(data[0])
    m = int(data[1]) 
    a = list(map(int, data[2:]))
    low = 1
    high = max(a)
    ans = 0
    while low <= high:
        mid = (low + high) // 2
        if mid == 0: break
        total_people = sum(x // mid for x in a)
        
        if total_people >= m:
            ans = mid      
            low = mid + 1
        else:
            high = mid - 1 
            
    print(ans)

if __name__ == "__main__":
    solve()
