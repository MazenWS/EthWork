package Steps;

public class Environment {
    // msg.sender (address payable)	Sender of the message.
    public static String MSG_SENDER = "msg.sender";

    //msg.value (uint)	Number of wei transferred with the message.
    public static String MSG_VALUE = "msg.value";

    //msg.data (bytes calldata)	Full calldata.
    public static String MSG_DATA = "msg.data";

    //msg.sig (bytes4)	First four bytes of the calldata.
    public static String MSG_SIG = "msg.sig";

    // block.coinbase (address payable)	Address of the current block miner.
    public static  String  BLOCK_COINBASE = "block.coinbase";

    // block.difficulty (uint)	Difficulty of the current block.
    public static  String  BLOCK_DIFFICULTY = "block.difficulty";

    //block.gaslimit (uint)	Gas limit of the current block.
    public static  String  BLOCK_GASLIMIT = "block.gaslimit";

    //block.number (uint)	Number of the current block.
    public static  String  BLOCK_NUMBER ="block.number";

    //block.timestamp (uint)	Timestamp of the current block (in seconds since Unix epoch).
    public static  String  BLOCK_TIMESTAMP=  "block.timestamp";

    //gasleft() returns (uint256)	Remaining gas.
    public static  String  GASLEFT=  "gasleft()";

    //now (uint)	Timestamp of the current block.
    public static  String  NOW=  "now";

    //tx.gasprice (uint)	Gas price of the transaction.
    public static  String  TX_GASPRICE=  "tx.gasprice";

    //tx.origin (address payable)	Sender of the transaction.
    public static  String  TX_ORIGIN=  "tx.origin";


}

//    blockhash(uint blockNumber) returns (bytes32)	Hash of the provided block.
//    It functions for 256 most recent blocks (not including the current ones).


