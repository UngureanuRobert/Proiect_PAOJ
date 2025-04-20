package util;

public class AuctionIdGenerator {
    private static int cnt = 0;
    public static synchronized String generate_id()
    {
        cnt++;
        return "AUCTION_" + String.format("%04d", cnt);
    }
}
