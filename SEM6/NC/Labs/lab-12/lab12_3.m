f = @(x) (x - 2)^2-log(x);

eps=1e-4;
N=100;
a=1;
b=2;

cb=bisection(f,a,b,N,eps)
