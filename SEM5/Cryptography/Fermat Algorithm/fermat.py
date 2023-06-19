import math as m


def main():
    n = int(input())

    t0 = m.floor(m.sqrt(n))
    print("t0 = " + str(t0))

    for i in range(1, 150):
        square = False
        result = pow(t0 + i, 2) - n
        root = m.sqrt(result)
        if int(root + 0.5) ** 2 == result:
            square = True
        print("t = t0 + " + str(i) + ": t^2 - n = " +
              str(result) + " pq: " + str(square))
        if square:
            f1 = t0 + i - root
            f2 = t0 + i + root
            print(str(f1) + "   and   " + str(f2) + " , s = " +
                  str(root) + ", t = " + str(t0 + i))
            break


if __name__ == "__main__":
    main()
