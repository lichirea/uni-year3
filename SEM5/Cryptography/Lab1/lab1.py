import time

def main():
    test(euclidean, 'Euclidean')
    test(dijkstra, 'Dijkstra\'s')
    test(exhaustive, 'Exhaustive')

#calculate the gcd of 28132 and 2416 (which is 4) 5000 times and time it
def test(func, algorithm_name):
    start = time.time()
    for i in range(5000):
        result = func(28132, 2416)
        if result != 4:
            raise 'GCD algorithm broke' 
    for i in range(5000):
        result = func(56, 12)
        if result != 4:
            raise 'GCD algorithm broke'        
    for i in range(5000):
        result = func(28, 14)
        if result != 14:
            raise 'GCD algorithm broke'  
    #test for 0
    result = func(0, 14)
    if result != 14:
        raise 'GCD algorithm broke'
    end = time.time()
    print(algorithm_name + ': ' + str((end - start))[0:6] + ' seconds')

#replace the bigger number with the smaller number, and the smaller number with the remainder of the division 
def euclidean(a, b):
    if a == 0 and b != 0:
        return b
    elif a != 0 and b ==0:
        return a

    #make sure a is the bigger number
    if a < b:
        temp = a
        a = b
        b = temp
    
    r = a % b
    while r:
        a = b
        b = r
        r = a % b
    return b;

#subtract the smaller number from the bigger number until they are equal
def dijkstra(x, y):
    if x == 0 and y != 0:
        return y
    elif x != 0 and y ==0:
        return x

    while(x != y):
        if(x > y):
            x -= y
        else:
            y -= x
    return x

#try all the possible solutions
def exhaustive(a, b):
    if a == 0 and b != 0:
        return b
    elif a != 0 and b ==0:
        return a
        
    i = min(a, b)
    while a % i != 0 or b % i != 0:
        i -= 1
    return i

if __name__ == "__main__":
    """ This is executed when run from the command line """
    main()