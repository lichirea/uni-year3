function H=hermite(t,f,d,x)
  l=length(t);
  z=zeros(1,2*l);  
  z(1:2:end)=t; 
  z(2:2:end)=t;
  fi=zeros(1,2*l);  
  fi(1:2:end)=f; 
  fi(2:2:end)=f;
  m=zeros(2*l,2*l); 
  m(:,1)=fi';
  m(1:2:end,2)=d';

  for i=1:l-1
    m(2*i,2)=(f(i+1)-f(i))./(t(i+1)-t(i));
  end
  
  for k=3:2*l
      for i=1:2*l-k+1
          m(i,k)=(m(i+1,k-1)-m(i,k-1))./(z(i+k-1)-z(i));
      end
  end

  lx=length(x);
  p=ones(lx,1);
  s=m(1,1)*ones(lx,1);
  for j=1:lx
    for i=1:2*l-1
      p(j)=p(j)*(x(j)-z(i));
      s(j)=s(j)+p(j)*m(1,i+1);
    end
  end

  H=s';
end