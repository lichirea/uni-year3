figure(1)
%1
subplot(2,2,1);
x=0:0.01:1;
l1=x;
plot(x,l1)

subplot(2,2,2)
l2=(3/2)*x.^2-(1/2);
plot(x,l2)

subplot(2,2,3)
l3=(5/2)*x.^3-(3/2)*x;
plot(x,l3)

subplot(2,2,4)
l4=(35/8)*x.^4-(15/4)*x.^2+(3/8);
plot(x,l4)

figure(2)
%2a
t=-1:0.01:1;
T1=cos(1*acos(t));
T2=cos(2*acos(t));
T3=cos(3*acos(t));
hold on
plot(t,T1,'r')
plot(t,T2,'g')
plot(t,T3,'b')

figure(3)
%2b
x=-1:0.01:1;
hold on
t0=1;
t1=x;
plot(x,t0);
plot(x,t1);

n = 10;
i = 2;
while i <= n
    t2=2*x.*t1-t0;
    plot(x,t2);
    t0=t1;
    t1=t2;
    i = i+1;
end
hold off

figure(4)
%3
clear x;
syms x;
f=exp(x);
T6 = taylor(f, x, 0, 'Order', 6);
T5 = taylor(f, x, 0, 'Order', 5);
T4 = taylor(f, x, 0, 'Order', 4);
T3 = taylor(f, x, 0, 'Order', 3);
T2 = taylor(f, x, 0, 'Order', 2);
T1 = taylor(f, x, 0, 'Order', 1);
x=[-1 3]
hold on
fplot(T6, x)
fplot(T5, x)
fplot(T4, x)
fplot(T3, x)
fplot(T2, x)
fplot(T1, x)
hold off



%4
% i = 0:6;
% h = 0.25;
% x = 1 + i.*h;
% f = sqrt(5*x.^2 + 1);
% finite_diff = lab2_4(x, f)
% function finite_diff = lab2_4(x, f)
%   n = length(x);
%   finite_diff = [f', zeros(n, n-1)];
%   for j=2:n
%     for i=1:n-j+1
%       finite_diff(i, j) = finite_diff(i+1, j-1) - finite_diff(i, j-1);
%     end
%   end
% end


%5
X=[2, 4, 6, 8];
Y=[4, 8, 14, 16];
tdd = divdiff(X,Y)
function TDD = divdiff(X, Y)
    [ p, m ] = size(X);
    TDD = zeros(m, m);
    TDD(:, 1) = Y';
    for j = 2 : m
        for i = 1 : (m - j + 1)
            TDD(i,j) = (TDD(i + 1, j - 1) - TDD(i, j - 1)) / (X(i + j - 1) - X(i));
        end
    end
end




