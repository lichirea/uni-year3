function x = newton (f, fd, x0, N, eps)
  x=x0;
  iter=0;
  while iter<=N
    x=x0-f(x)/fd(x);
    
    if(abs(x-x0))<eps
      fprintf("Number of iterations: %d\n", iter);
      return
    end
    display(x)
    iter = iter+1;
    x0=x;
   end
  
  disp('Too many iterations')
end