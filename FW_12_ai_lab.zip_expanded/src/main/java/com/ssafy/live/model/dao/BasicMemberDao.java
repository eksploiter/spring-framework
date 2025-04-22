/*
package com.ssafy.live.model.dao;

@Repository
public class BasicMemberDao implements MemberDao{

    @Override
    public int insert( Member member)  {
        String sql = "insert into member (name, email, password) values(?,?,?)";
        int result = -1;
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getPassword());
            result = pstmt.executeUpdate();
        }
        return result;
    }
    
        @Override
    public int update( Member member)  {
        int result = 0;
        String sql = "update member set name=?, password=?, role = ? where email=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getRole());
            pstmt.setString(4, member.getEmail());
            result = pstmt.executeUpdate();
        }
        return result;
    }
    
    @Override
    public int delete( int mno)  {
        int result = -1;
        String sql = "delete from member where mno=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, mno);
            result = pstmt.executeUpdate();
        }
        return result;
    }
    
    @Override
    public int updateProfile( String email, InputStream blob)  {
        int result = 0;
        String sql = "update member set profile=? where email=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setBlob(1, blob);
            pstmt.setString(2, email);
            result = pstmt.executeUpdate();
        }
        return result;
    }
    
    @Override
    public Member select( String email)  {
        Member member = null;
        String sql = "select * from member where email=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                member = new Member();
                member.setMno(rset.getInt("mno"));
                member.setName(rset.getString("name"));
                member.setEmail(rset.getString("email"));
                member.setPassword(rset.getString("password"));
                member.setRole(rset.getString("role"));
            }
        }
        return member;
    }
    
    @Override
    public List<Member> search(Connection con)  {
        List<Member> list = new ArrayList<>();
        String sql = "select * from member order by mno desc";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                Member member = new Member();
                member.setMno(rset.getInt("mno"));
                member.setName(rset.getString("name"));
                member.setEmail(rset.getString("email"));
                list.add(member);
            }
        }
        return list;
    }
    
    @Override
    public Member selectDetail( String email)  {
        Member member = null;
        String sql = """
                select m.*, a.*
                from member m left join address a using(mno)
                where m.email=?""";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                int mno = rset.getInt("mno");
                String name = rset.getString("name");
                String pass = rset.getString("password");
                String role = rset.getString("role");
                Integer ano = rset.getInt("ano");
                String title = rset.getString("title");
                String address = rset.getString("address");
                String detailAddress = rset.getString("detail_address");
                String x = rset.getString("x");
                String y = rset.getString("y");
                Blob profile = rset.getBlob("profile");
                if (member == null) {
                    member = new Member(mno, name, email, pass, role);
                    if (profile != null) {
                        member.setProfile(Base64.getEncoder().encodeToString(profile.getBytes(1L, (int) profile.length())));
                    }
                }
                if (title != null) {
                    Address addr = new Address(ano, mno, title, address, detailAddress, x, y);
                    member.getAddresses().add(addr);
                }
            }
        }
        return member;
    }
    
    public int getTotalCount( SearchCondition condition)  {
        int result = 0;
        String sql = null;
        boolean hasKeyWord = condition.hasKeyword();
    
        sql = hasKeyWord ? "select count(*) from member where %s like ?".formatted(condition.getKey())
                : "select count(*) from member";
    
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            if (hasKeyWord) {
                pstmt.setString(1, "%" + condition.getWord() + "%");
            }
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }
        return result;
    }
    
    public List<Member> search( SearchCondition condition)  {
        List<Member> members = new ArrayList<>();
        String sql = null;
        boolean hasKeyWord = condition.hasKeyword();
        sql = hasKeyWord
                ? "select * from member where %s like ?  order by mno desc limit ?,?".formatted(condition.getKey())
                : "select * from member order by mno desc limit ?,?";
    
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            int idx = 1;
            if (hasKeyWord) {
                pstmt.setString(idx++, "%" + condition.getWord() + "%");
            }
            pstmt.setInt(idx++, condition.getOffset());
            pstmt.setInt(idx++, condition.getItemsPerPage());
    
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Member member = new Member();
                member.setMno(rs.getInt("mno"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPassword(rs.getString("password"));
                member.setRole(rs.getString("role"));
                members.add(member);
            }
        }
        return members;
    }
    


}
    */
