function x = lup(A, b)
  [L, U, P] = LUP_factorization(A);
  y = forward(L, P*b);
  x = backward(U, y);
end

function [L, U, P] = LUP_factorization(A)
  n = size(A, 1);
  L = eye(n);
  P = eye(n);
  U = A;
  
  for k = 1 : n-1
    [~, pivot] = max(abs(U(k:n, k)));
    pivot = pivot + k - 1;
    
    U([k, pivot], k:n) = U([pivot, k], k:n);
    L([k, pivot], 1:k-1) = L([pivot, k], 1:k-1);
    P([k, pivot], :) = P([pivot, k], :);
    
    for i = k+1 : n
      L(i, k) = U(i, k) / U(k, k);
      U(i, k:n) = U(i, k:n) - L(i, k) * U(k, k:n);
    end
  end
end