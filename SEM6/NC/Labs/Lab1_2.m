p1=[2,-5,0,8];
polyval(p1,2);

p2=[1,-5,-17,21]
roots(p2);

%III graphs
%1
figure(1)
x=0:0.01:1;
y=exp(10*x.*(x-1)).*sin(12*pi*x);

plot(x,y,'*r');

figure(2)
x=0:0.01:1;
y=3*exp(5*x.^2-1).*cos(12*pi*x)
plot(x,y,'*y');


%2
figure(3)
a=5;
b=6;
t=0:0.1:10*pi
x=(a+b)*cos(t)-b*cos(((a/b)+1)*t);
y=(a+b)*sin(t)-b*sin(((a/b)+1)*t);
plot(x,y);

%3

%M1 plot(x,f1) hold on plot(x,f2); plot(x,f3); hold off
%M2 plot(x,f1,x,f2,x,f3)
figure(4)
x=0:0.1:2*pi;
f1=cos(x)
f2=sin(x)
f3=cos(2*x)

plot(x,f1,'r',x,f2,'b',x,f3,'g');
%4
figure(5)
x1=-1:0.01:0;
x2=0:0.01:1;
f1=x1.^3+sqrt(1-x1);
f2=x2.^3-sqrt(1-x2);
plot(x1,f1,'r*',x2,f2,'b*');


%5
figure(6)
x1=0:2:50;
x2=1:2:50;
f1=x1/2;
f2=3*x2+1;
plot(x1,f1,'r*',x2,f2,'b*');

 
%7
figure(8)
[x,y]=meshgrid(-2:0.01:2, -4:0.01:4);
g=exp(-((x-(1/2)).^2+(y-1/2).^2));
mesh(x,y,g);

%6
figure(7)
fplot(recursive(5))
function r = recursive(n)
    if n <= 0
        r = 1;
    else
        r=1+(1/recursive(n-1));
    end
end

