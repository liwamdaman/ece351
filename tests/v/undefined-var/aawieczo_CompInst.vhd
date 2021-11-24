entity blackBox is port (
    X: in bit;
    Y: out bit
);
end blackBox;

architecture blackBoxArchitecture of blackBox is
begin
    Y <= X;
end blackBoxArchitecture;


entity failingCompInst is port (
    q, w : in bit;
    e, r, t: out bit
);
end failingCompInst;


architecture failingCompInstArchitecture of failingCompInst is
begin
    workingBlackBox : entity work.full_adder port map(q, e);
    failingBlackBox : entity work.full_adder port map(a, r);
    
    t <= q and w;
end failingCompInstArchitecture;
