package uk.ac.cityofglasgowcollege.assessement3_pmg;

/**
 * Created by nealnisbet on 27/02/2018.
 */

public class Comments {
    //private properties
    private String username;
    private String productId;
    private String comments;
    private int reviewRating;

    //public getter functions
    public String getUsername()
    {
        return username;
    }

    public String getProductId()
    {
        return productId;
    }

    public String getComments()
    {
        return comments;
    }

    public int getReviewRating()
    {
        return reviewRating;
    }


    //public setter methods
    public void setUsername(String usernameIn)
    {
        username = usernameIn;
    }

    public void setProductId(String productIdIn)
    {
        productId = productIdIn;
    }

    public void setComments(String commentsIn)
    {
        comments = commentsIn;
    }

    public void setReviewRating(int reviewRatingIn)
    {
        reviewRating = reviewRatingIn;
    }
}
