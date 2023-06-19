function aprox = RectangleRule(f,a,b,n)
    h = (b-a)/n;
    s=0;
    for i =0:n-1
        xn= a + (i * h);
        s = s + f(xn);
    end
    aprox = h * s;
end

