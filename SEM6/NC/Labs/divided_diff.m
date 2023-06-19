function fun = divided_diff(x, f)
  n = length(x);
  fun = [f', zeros(n, n-1)];
  for k = 2:n  
    for i = 1:n-k+1
      fun(i,k) = (fun(i+1,k-1)-fun(i,k-1))/(x(i+k-1)-x(i)); 
    end
  end
end