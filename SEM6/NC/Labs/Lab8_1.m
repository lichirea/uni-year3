RectangleRule(@(x) log(x),1,3,2)
TrapezoidalRule(@(x) log(x),1,2.391,22)
SimpsonsRule(@(x) log(x),1.5,2.5115,1000)
RombergMethod(@(x) 1/(2+cos(x)),0,2*pi,10)