f = @(x) x-cos(x);
fd = @(x) sin(x) + 1;

x0=pi/4;
eps = 1e-4;
N=100;

x=newton(f, fd, x0, N, eps)