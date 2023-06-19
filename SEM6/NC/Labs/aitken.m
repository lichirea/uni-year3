function fun = aitken(x, y, a)
  err=1e-3; 
  [fun, I] = sort(abs(x-a));
  x = x(I);
  y = y(I);
  n=length(x);

  ak=zeros(n,n);
  ak(:,1)=y';
  for i=1:n
      for j=1:i-1
         ak(i,j+1) = (1/(x(i)-x(j))) * (ak(j,j) * (x(i)-a) - ak(i,j) * (x(j)-a));
      end
      if i>1 && abs(ak(i,i)-ak(i-1,i-1))<=err
            fun = ak(i,i);
            return
      end
   end