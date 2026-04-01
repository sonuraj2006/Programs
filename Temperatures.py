# cook your dish here
t=int(input())
for i in range(t):
    x=int(input())
    k=list(map(int,input().split()))
    count=0
    for j in range(x):
        if k[j]!=min(k) and k[j]!=max(k):
            count=count+1
    print(count)