entity firstEntity is port (
    q,w,e,r,t: in bit;
    a: out bit
);
end firstEntity;

architecture firstEntityArchitecture of firstEntity is 
begin  
    a <= not (q or w or e or r or t);
end firstEntityArchitecture;



entity secondEntity is port (
    q: in bit;
    a: out bit
);
end secondEntity;

architecture secondEntityArchitecture of secondEntity is 
begin  
    a <= not (q or w or e or r or t);
end secondEntityArchitecture;
