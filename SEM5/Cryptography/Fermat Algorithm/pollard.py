import math as m


def main():
    n = int(input())

    xarray = [None] * 100
    xarray[0] = 2

    for x in range(1, 50):
        xarray[x] = (pow(xarray[x-1], 2) + 1) % n
        print("x" + str(x) + " = " + str(xarray[x]))

    print()

    for j in range(1, 500):
        d = m.gcd(abs(xarray[2*j] - xarray[j]), n)
        print("d=" + str(d) + " with |x" + str(2*j) + "-x" +
              str(j) + "| " + str(xarray[2*j]) + " " + str(xarray[j]))
        if 1 < d and d < n:
            print("d=" + str(d) + " IS A NON TRIVIAL FACTOR OF N")
            break


if __name__ == "__main__":
    main()
