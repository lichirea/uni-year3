function [x, nit] = gauss_seidel(A, b, x0, err, maxnit)
    nit = 0;
    x = x0;
    L = tril(A, -1);
    U = triu(A, 1);
    D = diag(diag(A));
    T = -inv(D + L) * U;
    c = (D + L) \ b;
    alpha = norm(T, inf);
    
    for i = 1:maxnit
        nit = nit + 1;
        x_copy = x;
        x = T * x_copy + c;
        error = norm(x - x_copy, inf);  % Compute the error using norm
        
        if error <= err
            break;
        end
    end
end