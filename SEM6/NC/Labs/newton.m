function fun = newton(x,f,xx)
  n=length(x)-1;
  m=divided_diff(x, f);
  l=length(xx);
  p=ones(1,l);
  s=m(1,1)*ones(1,l);
  for j=1:l
    for i=1:n
      p(j)=p(j)*(xx(j)-x(i));
      s(j)=s(j)+p(j)*m(1,i+1);
    end
  end
  fun=s;
end