entity IfIf is port(
    I3, I2, I1, I0, S  : in bit;
    O0, O1  : out bit
);
end IfIf;
architecture behv1 of IfIf is

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
            if ( ( not ( ( ( S and ( not ( '1' ) ) ) or ( ( not ( S ) ) and '1' ) ) ) ) ) then
                O1 <= I2;
            else
                O1 <= I3;
            end if;
        end process;
end behv1;