package auctionsystem;
import java.util.Date;

/**
 *
 * @author ASUSPC
 */
public class BlindAuction extends Auction {
    private int bidCounter=1;
    
    public BlindAuction(double startPrice, Date startTime, Date endTime){
        super(startPrice, startTime, endTime);
        AuctionType = BLIND_AUCTION;
    }
    
    public BlindAuction(double startPrice, BiddingStack<Double,Bidder,Date> bidStack, Date startTime, Date endTime){
	super( startPrice, bidStack, startTime, endTime);
        AuctionType = BLIND_AUCTION;
    }
    
    @Override
    public void pushBid(Double bid, Date currentTime, Bidder bidder){
        if(bidCounter==1){
            if(currentTime.equals(endTime)){
                bidStack.push(bid, bidder, currentTime);
                bidder.addBidFrequency();
            }
        }
    }
    
        public boolean checkTie(){
        double highestPrice = getHighestBid();
        int numOfTie = 0;
        for(int i = 0; i < bidStack.bidPriceList.size(); i++){
            if(bidStack.bidPriceList.get(i) == highestPrice){
                numOfTie++;
            }
        }
        if(numOfTie > 1)
            return true;
        else 
            return false;
    }
    
    public int tieWinnerPosition (){
        double highestPrice = getHighestBid();
        int numOfTie = 0;
        for(int i = 0; i < bidStack.bidPriceList.size(); i++){
            if(bidStack.bidPriceList.get(i) == highestPrice){
                numOfTie++;
            }
        }
        int[] holdPosition = new int[numOfTie];
        int indexOfTie = 0;
        for(int i = 0; i < bidStack.bidPriceList.size(); i++){
            if(bidStack.bidPriceList.get(i) == highestPrice){
                holdPosition[indexOfTie] = i;
                indexOfTie++;
            }
        }
        return holdPosition[0];
    }
}
