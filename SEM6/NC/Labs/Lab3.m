figure(1)
clear all;
x = [1930 1940 1950 1960 1970 1980];
y = [123203 131669 150697 179323 203212 226505];
sum=0;
for i=1:length(x)
    p=1;
    for j=1:length(x)
        if j~=i
            c = poly(x(j))/(x(i)-x(j));
            p = conv(p,c);
        end
    end
    term = p*y(i);
    sum= sum + term;
end
polyval(sum,1955)
polyval(sum,1995)
years = 1920:1:2000;
plot(years, polyval(sum,years))
grid on;

figure(2)
clear all;
x = [100 121 144];
y = [sqrt(100) sqrt(121) sqrt(144)];
sum=0;
for i=1:length(x)
    p=1;
    for j=1:length(x)
        if j~=i
            c = poly(x(j))/(x(i)-x(j));
            p = conv(p,c);
        end
    end
    term = p*y(i);
    sum= sum + term;
end
polyval(sum,115)
numbers = 100:1:225;
plot(numbers, polyval(sum,numbers))

figure(3)
clear all;
x = 0:0.01:10;
f = (1+cos(pi*x))/(1+x);
plot(f,x)

figure(4)
clear all;
x = 0:0.476:1;
f = @(x) (1+cos(pi*x))/(1+x);
y = zeros(length(x));
for i=1:length(x)
    y(i) = f(x(i));
end

sum=0;
for i=1:length(x)
    p=1;
    for j=1:length(x)
        if j~=i
            c = poly(x(j))/(x(i)-x(j));
            p = conv(p,c);
        end
    end
    term = p*y(i);
    sum= sum + term;
end
polyval(sum,115)
numbers = 100:1:225;
plot(numbers, polyval(sum,numbers))

