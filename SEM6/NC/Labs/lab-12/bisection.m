function c = bisection(f,a,b,N,eps)
  if f(a)*f(b)<=0
    iter=0;
    while iter<=N
      c=(a+b)/2;
      
      if f(a)*f(c)<=0
        b=c;
      else
        a=c;
      end
      
      if abs(f(c))<eps
        fprintf("Number of iterations (bisection): %d\n", iter);
        return
      end
      
      iter=iter+1;
    end
    
    disp('Too many iterations (bisection)')
  end
  
  disp("Error (bisection)");
  end