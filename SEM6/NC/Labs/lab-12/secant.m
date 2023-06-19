function x = secant (f,x0,x1,N,eps)
  x=x1;
  iter=1;
  while iter<=N
    
    x=x1-f(x1)*((x1-x0)/(f(x1)-f(x0)));
    iter=iter+1;
    x0=x1;
    x1=x;
    
    if(abs(x1-x0))<eps
      fprintf("Number of iterations: %d\n", iter);
      return
    end
   end
  
  disp('Too many iterations')
endfunction