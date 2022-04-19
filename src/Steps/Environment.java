package Steps;

public class Environment {
    public static String MSG_SENDER = "msg.sender";
    public static String MSG_VALUE = "msg.value";
}

//    blockhash(uint blockNumber) returns (bytes32)	Hash of the provided block.
//    It functions for 256 most recent blocks (not including the current ones).

//        block.coinbase (address payable)	Address of the current block miner.
//        block.difficulty (uint)	Difficulty of the current block.
//        block.gaslimit (uint)	Gas limit of the current block.
//        block.number (uint)	Number of the current block.
//        block.timestamp (uint)	Timestamp of the current block (in seconds since Unix epoch).
//        gasleft() returns (uint256)	Remaining gas.
//        msg.data (bytes calldata)	Full calldata.
//        msg.sender (address payable)	Sender of the message.
//        msg.sig (bytes4)	First four bytes of the calldata.
//        msg.value (uint)	Number of wei transferred with the message.
//        now (uint)	Timestamp of the current block.
//        tx.gasprice (uint)	Gas price of the transaction.
//        tx.origin (address payable)	Sender of the transaction.
