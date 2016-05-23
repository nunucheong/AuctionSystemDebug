package auctionsystem;

import java.util.Date;


/**
 *
 * @author ASUSPC
 */
public class VickeryAuction extends Auction {
    private int bidCounter=1;
    
    public VickeryAuction(){}
    
    public VickeryAuction(double startPrice, Date startTime, Date endTime){
        super(startPrice, startTime, endTime);
        AuctionType = VICKERY_AUCTION;
    }
    
    public VickeryAuction(double startPrice, BiddingStack<Double,Bidder,Date> bidStack, Date startTime, Date endTime){
	super( startPrice, bidStack, startTime, endTime);
        AuctionType = VICKERY_AUCTION;
    }
    
    public void setBidCount(int bidCounter){
        this.bidCounter = bidCounter;
    }
    
    @Override
    public void pushBid(Double bid, Date currentTime, Bidder bidder){
       if(super.bidStack.bidderList.size() !=0)
            for (int i = 0; i < super.bidStack.bidderList.size(); i++){
                if(super.bidStack.bidderList.get(i).getName().equals(bidder.getName())){
                    System.out.println("You can only bid once.");
                }
                else{
                    bidStack.push(bid, bidder, currentTime);
                    bidder.addBidFrequency();
                  }
            }
        else{
            bidStack.push(bid, bidder, currentTime);
            bidder.addBidFrequency();
        }
    }
    
    public double getSecondHighestBid(){
        if(bidStack.bidPriceList.size() < 2)
            return getHighestBid();
        else{
            Triplet<Double, Bidder, Date> hold = this.bidStack.pop();
            Triplet<Double, Bidder, Date> secondBid = this.bidStack.peek();
            this.bidStack.push(hold.getKey(), hold.getValue(), hold.getThird());
            return secondBid.getKey();
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
