function [x, nit] = jacobi(A, b, x0, err, maxnit)
    nit = 0;
    x = x0;
    D = diag(diag(A));
    R = A - D;
    T = -inv(D) * R;
    c = D\b;
    alpha = norm(T, inf);
    
    for i = 1:maxnit
        nit = nit + 1;
        x_copy = x;
        x = T * x_copy + c;
        error = max(abs(x_copy - x));
        
        if error <= (1 - alpha) / alpha * err
            break;
        end
    end
end
