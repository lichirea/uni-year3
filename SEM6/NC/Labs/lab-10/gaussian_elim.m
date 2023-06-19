function x = gaussian_elim(A, b)
  n = size(A, 1);

  x = zeros(n, 1);
  l = zeros(n, n-1);

  for k = 1 : n-1
    for p = k+1 : n
      if (abs(A(k, k)) < abs(A(p, k)))
        A([k p], :) = A([p k], :);
        b([k p]) = b([p k]);
      end
    end

    for i = k+1 : n
      l(i, k) = A(i, k) / A(k, k);
        for j = k+1 : n
          A(i, j) = A(i, j) - l(i, k) * A(k, j);
        end
        
        b(i) = b(i) - l(i, k) * b(k);
    end
  end

  for k = 1 : n-1
    for i = k+1 : n
      A(i, k) = 0;
    end
  end

  x = backward(A, b);
end