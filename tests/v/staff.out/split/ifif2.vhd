entity IfIf2 is port(
    I4, I5, I3, I2, I1, I0, S  : in bit;
    O0, O1, O2, O3  : out bit
);
end IfIf2;
architecture behv1 of IfIf2 is

begin
    process ( S, I0, I1  ) 
        begin
            if ( ( not ( ( ( S and ( not ( '0' ) ) ) or ( ( not ( S ) ) and '0' ) ) ) ) ) then
                O0 <= I0;
            else
                O0 <= I1;
            end if;
        end process;
    process ( S, I2, I3  ) 
        begin
            if ( ( not ( ( ( S and ( not ( '0' ) ) ) or ( ( not ( S ) ) and '0' ) ) ) ) ) then
                O1 <= I2;
            else
                O1 <= I3;
            end if;
        end process;
    process ( S, I4, I5  ) 
        begin
            if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then
                O2 <= I4;
            else
                O2 <= I5;
            end if;
        end process;
    process ( S, I5, I3  ) 
        begin
            if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then
                O3 <= I5;
            else
                O3 <= I3;
            end if;
        end process;
end behv1;