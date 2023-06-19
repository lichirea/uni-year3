f=@(x) x^3-x^2-1;

x0=1;
x1=2;
eps=1e-4;
N=100;

x=secant(f,x0,x1,N,eps)